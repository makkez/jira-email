package ru.makkez.jiraemailissue;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.EncryptionMethod;

import java.io.File;
import java.util.List;

public class Archiver {

    public ZipFile createArchiveWithPassword(String file, String password) throws ZipException {
        ZipParameters zipParameters = new ZipParameters();
        zipParameters.setEncryptFiles(true);
        zipParameters.setEncryptionMethod(EncryptionMethod.AES);
        zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);
        ZipFile result = new ZipFile("testArchive.zip", password.toCharArray());
        result.addFile(new File(file), zipParameters);
        return result;
    }

    public File extractFile(String archive, String password) throws ZipException {
        ZipFile zipFile = new ZipFile(archive);
        if (zipFile.isEncrypted()) {
            //Your ZIP password
            zipFile.setPassword(password.toCharArray());
        }
        List fileHeaderList = zipFile.getFileHeaders();

        for (int i = 0; i < fileHeaderList.size(); i++) {
            FileHeader fileHeader = (FileHeader) fileHeaderList.get(i);
            //Path where you want to Extract
            zipFile.extractFile(fileHeader, "extracted");
            System.out.println("Extracted");
        }
        return null;
    }
}
