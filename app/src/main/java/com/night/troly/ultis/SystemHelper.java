package com.night.troly.ultis;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by night on 06/05/2017.
 */

public class SystemHelper {
    private static final String ASSETS_NAME = "data.zip";
    private static final String DATABASE_NAME = "data.db";

    private static SystemHelper sHelper;

    private Context mContext;

    private SystemHelper(Context context) {
        mContext = context;
    }

    public static SystemHelper getInstance(Context context) {
        if (sHelper == null) {
            sHelper = new SystemHelper(context);
        }

        return sHelper;
    }

    public void extractZippedDatabases(List<String> fileList) {
        String dirPath = mContext.getFilesDir().getParent() + "/databases/";
        extractZippedFile(dirPath, ASSETS_NAME, fileList);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void extractZippedFile(String dirPath, String assetName, List<String> fileList) {
        // Check if directory exists, if it's not then create new
        File f = new File(dirPath);
        if (!f.exists()) {
            f.mkdir();
        }

        try {
            InputStream is = mContext.getAssets().open(assetName);
            ZipInputStream zipStream = new ZipInputStream(is);
            ZipEntry zipEntry;

            while ((zipEntry = zipStream.getNextEntry()) != null) {
                String entryName = zipEntry.getName();
                // Skip directory and file not in the list
                if (zipEntry.isDirectory() || !fileList.contains(entryName)) {
                    continue;
                }

                String dbPath = dirPath + entryName;
                FileOutputStream fileOut = new FileOutputStream(dbPath);
                byte[] buffer = new byte[4096];
                int byteCount;

                while ((byteCount = zipStream.read(buffer, 0, 4096)) != -1) {
                    fileOut.write(buffer, 0, byteCount);
                }
                zipStream.closeEntry();
                fileOut.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
