package com.ddm.quartz;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yunpeng.song on 6/26/2018.
 */
public class PhotoMatchTask {

    private static final Logger logger = LoggerFactory.getLogger(PhotoMatchTask.class);

    //@Value("${aToB}")
    private String aToB;

    //@Value("${file_suffix}")
    private String[] file_suffix;



    private FilenameFilter filenameFilter = new FilenameFilter() {
        @Override
        public boolean accept(File dir, String name) {
            return FilenameUtils.isExtension(name.toLowerCase(), file_suffix);
        }
    };

    private Map<File, Long> doubleCheckMap = new ConcurrentHashMap();

    /**
     * Cause there is not general solution for checking whether a file is completely written,
     * here we use triple check:
     * 1, file can write
     * 2, rw lock
     * 3, file size remains the same in a period of time.
     */
    public void milestonePhotoRecordMatch() {
        File sharedDir = FileUtils.getFile(aToB);
        if (!sharedDir.isDirectory()) {
            return;
        }
        if (sharedDir.list(filenameFilter).length == 0) {
            return;
        }

        for (File file : sharedDir.listFiles()) {
            if (!file.canWrite() || !isCompletelyWritten(file)) {
                continue;
            }
            if (!doubleCheckMap.containsKey(file)) {
                doubleCheckMap.put(file, file.length());
                continue;
            }
            if (doubleCheckMap.get(file) == file.length()) {
                // Move and Insert file to pathology_file.


               //Save link info to DB

                doubleCheckMap.remove(file);
                continue;
            } else if (file.length() > doubleCheckMap.get(file)) {
                doubleCheckMap.put(file, file.length());
            }
        }

        //Clean not exist file, just in case invalid data left over in doublecheck map
        for (Iterator<File> it = doubleCheckMap.keySet().iterator(); it.hasNext(); ) {
            File file = it.next();
            if (!file.exists()) {
                it.remove();
            }
        }

    }

    private boolean isCompletelyWritten(File file) {
        RandomAccessFile stream = null;
        try {
            stream = new RandomAccessFile(file, "rw");
            return true;
        } catch (Exception e) {
            logger.info("Skipping file " + file.getName() + " for this iteration due it's not completely written");
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    logger.error("Exception during closing file " + file.getName());
                }
            }
        }
        return false;
    }


}
