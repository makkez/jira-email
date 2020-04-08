package ru.makkez.jiraemailissue;

import net.lingala.zip4j.exception.ZipException;
import org.junit.jupiter.api.Test;

public class ArchiverTest {

    @Test
    public void whenCreateArchiveWithPassword() throws ZipException {
        Archiver archiver = new Archiver();
        archiver.createArchiveWithPassword("src/test/resources/testData.txt", "123");
    }

    @Test
    public void whenExtractFile() throws ZipException {
        Archiver archiver = new Archiver();
        archiver.extractFile("testArchive.zip", "123");
    }
}
