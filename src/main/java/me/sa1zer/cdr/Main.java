package me.sa1zer.cdr;

import me.sa1zer.cdr.manager.CdrCalculateManager;
import me.sa1zer.cdr.manager.CdrParseManager;
import me.sa1zer.cdr.manager.ReportManager;

public class Main {

    public static void main(String[] args) {
        CdrParseManager cdrParseManager = new CdrParseManager();
        ReportManager reportManager = new ReportManager();
        CdrCalculateManager cdrCalculateManager = new CdrCalculateManager(cdrParseManager,
                reportManager);

        cdrCalculateManager.calculate();
    }
}
