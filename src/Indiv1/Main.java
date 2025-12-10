package Indiv1;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        OutputCometData printData = new OutputCometData();
        CreateCometData cometDataAll = new CreateCometData();
        CometManager manager = new CometManager();

        cometDataAll.getCometDataFromFile("src/Indiv1/data_comets.csv");
        cometDataAll.getCometDataFromFile("src/Indiv1/data_comets_date.txt");

        List<Comet> cometsData = cometDataAll.getComets();
        List<CometDateInfo> cometsDatesData = cometDataAll.getCometDateInfos();

        printData.printAllComets(cometsData);
        printData.printAllCometDates(cometsDatesData);

        manager.findCometsWithPerihelionDates(cometsData, cometsDatesData, printData);
    }
}
