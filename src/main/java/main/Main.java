package main;

import com.opencsv.CSVWriter;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) {

        Map<String, Integer> gearValueMap = new HashMap<>();

        gearValueMap.put("Mk 3 Sienar Holo Projector", Constants.MK3_DISC);
        gearValueMap.put("Mk 4 Sienar Holo Projector", Constants.MK4_DISC + Constants.STUN_CUFF);
        gearValueMap.put("Mk 4 Chiewab Hypo Syringe", Constants.BLUE_HAIRDRYER);
        gearValueMap.put("Mk 6 Chiewab Hypo Syringe", Constants.HAIRDRYER);

        gearValueMap.put("Mk 8 Neuro-Saav Electrobinoculars", Constants.MK_8_BINOCS);
        gearValueMap.put("MK 8 Neuro-Saav Electrobinoculars", Constants.MK_8_BINOCS);
        gearValueMap.put("Mk 9 Neuro-Saav Electrobinoculars", Constants.MK_9_BINOCS);
        gearValueMap.put("Mk 10 Neuro-Saav Electrobinoculars", Constants.MK_10_BINOCS);

        gearValueMap.put("Mk 5 CEC Fusion Furnace", Constants.MK5_FURNACE + Constants.HAIRDRYER + Constants.MK3_DISC);
        gearValueMap.put("Mk 6 CEC Fusion Furnace", Constants.MK6_FURNACE + Constants.MK_8_BINOCS);

        gearValueMap.put("Mk 7 TaggeCo Holo Lens", Constants.MK3_DISC);
        gearValueMap.put("Mk 9 TaggeCo Holo Lens", Constants.MK_2_PURPLE_STICK + Constants.MK_6_BRADS + Constants.MK_8_BINOCS);
        gearValueMap.put("Mk 10 TaggeCo Holo Lens", Constants.MK10_TRAPEZOID);

        gearValueMap.put("Mk 3 Czerka Stun Cuffs", Constants.STUN_CUFF);
        gearValueMap.put("Mk 4 Czerka Stun Cuffs", Constants.MK9_BALL + Constants.CARABANTI + Constants.STUN_GUN + Constants.MK_10_BINOCS);

        gearValueMap.put("Mk 5 Nubian Design Tech", Constants.MK4_LAPTOP);
        gearValueMap.put("Mk 6 Nubian Design Tech", Constants.MK6_WHITE_DISC);
        gearValueMap.put("Mk 6 Nubian Security Scanner", Constants.MK4_LAPTOP);
        gearValueMap.put("Mk 7 Nubian Security Scanner", Constants.MK7_SCANNER);

        gearValueMap.put("Mk 2 Zaltin Bacta Gel", Constants.MK_2_PURPLE_STICK);
        gearValueMap.put("Mk 3 Zaltin Bacta Gel", Constants.MK4_LAPTOP + Constants.HAIRDRYER + Constants.MK_3_PURPLE_STICK);
        gearValueMap.put("Mk 4 Zaltin Bacta Gel", Constants.MK_4_PURPLE_STICK);

        gearValueMap.put("Mk 8 Loronar Power Cell", Constants.MK5_MEDPACK + Constants.CARABANTI + Constants.MK5_GRENADE + Constants.MK_6_BRADS);

        gearValueMap.put("Mk 8 BlasTech Weapon Mod", Constants.MK4_LAPTOP + Constants.MK8_CIRCLE);
        gearValueMap.put("Mk 9 BlasTech Weapon Mod", Constants.STUN_CUFF * 2);
        gearValueMap.put("Mk 10 BlasTech Weapon Mod", Constants.MK10_CIRCLE);
        gearValueMap.put("Mk 11 BlasTech Weapon Mod", Constants.MK11_CIRCLE);

        gearValueMap.put("Mk 5 A/KT Stun Gun", Constants.CARABANTI + Constants.STUN_GUN);
        gearValueMap.put("Mk 6 A/KT Stun Gun", Constants.GOLD_EYEBALL + Constants.MK_4_PURPLE_STICK + Constants.CARABANTI + Constants.STUN_GUN);

        gearValueMap.put("Mk 3 Chedak Comlink", Constants.MK3_TAG);
        gearValueMap.put("Mk 4 Chedak Comlink", Constants.STUN_CUFF * 2 + Constants.MK4_TAG);
        gearValueMap.put("Mk 5 Chedak Comlink", Constants.STUN_CUFF + Constants.MK4_TAG + Constants.MK9_DATA_PAD + Constants.MK_4_PURPLE_STICK);
        gearValueMap.put("Mk 3 Carbanti Sensor Array", Constants.CARABANTI);

        gearValueMap.put("Mk 5 Merr-Sonn Thermal Detonator", Constants.MK5_GRENADE + Constants.CARABANTI);
        gearValueMap.put("Mk 6 Merr-Sonn Thermal Detonator", Constants.MK6_GRENADE);
        gearValueMap.put("Mk 6 Merr-Sonn Shield Generator", Constants.MK4_LAPTOP * 2);
        gearValueMap.put("Mk 7 Merr-Sonn Shield Generator", Constants.MK7_TABLET);

        gearValueMap.put("Mk 7 BioTech Implant", Constants.MK7_BALL);
        gearValueMap.put("Mk 8 BioTech Implant", Constants.GOLD_EYEBALL);
        gearValueMap.put("Mk 9 BioTech Implant", Constants.MK9_BALL);

        gearValueMap.put("Mk 4 Athakam Medpac", Constants.HAIRDRYER);
        gearValueMap.put("Mk 5 Athakam Medpac", Constants.MK5_MEDPAC);

        gearValueMap.put("Mk 5 Arakyd Droid Caller", Constants.MK5_BRADS);
        gearValueMap.put("Mk 6 Arakyd Droid Caller", Constants.MK_6_BRADS);

        gearValueMap.put("Mk 9 BAW Armor Mod", Constants.MK9_BALL + Constants.MK5_GRENADE + Constants.MK4_TAG + Constants.STUN_CUFF);

        gearValueMap.put("Mk 4 SoroSuub Keypad", Constants.MK4_LAPTOP);
        gearValueMap.put("Mk 6 SoroSuub Keypad", Constants.MK5_MEDPAC + Constants.MK_9_BINOCS + Constants.CARABANTI + Constants.MK7_SCANNER);
        gearValueMap.put("Mk 9 Fabritech Data Pad", Constants.MK9_DATA_PAD);

        File resourcesFolder = new File("src/main/resources");
        File[] listOfFiles = resourcesFolder.listFiles();

        File file = new File("src/main/output/output.csv");
        CSVWriter writer = null;
        try {
            FileWriter outputfile = new FileWriter(file);
            writer = new CSVWriter(outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        writer.writeNext(new String[]{"", "G1", "G2", "G3", "G4", "G5", "G6", "G7", "G8", "G9", "G10", "G11"});

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {

                try {
                    List<String> gearList = new ArrayList<>();
                    List<String> outputList = new ArrayList<>();
                    outputList.add(listOfFiles[i].getName());

                    Document doc = Jsoup.parse(listOfFiles[i], "UTF-8");
                    Elements links = doc.select("a[href^=\"/db/gear\"]");

                    links.forEach((link) -> {
                        //System.out.println(link.attr("title"));
                        String gearName = link.attr("title");
                        if (StringUtils.isNotEmpty(gearName)){
                            gearList.add(gearName);
                        }
                    });

                    List g1 = gearList.subList(0, 6);
                    List g2 = gearList.subList(6, 12);
                    List g3 = gearList.subList(12, 18);
                    List g4 = gearList.subList(18, 24);
                    List g5 = gearList.subList(24, 30);
                    List g6 = gearList.subList(30, 36);
                    List g7 = gearList.subList(36, 42);
                    List g8 = gearList.subList(42, 48);
                    List g9 = gearList.subList(48, 54);
                    List g10 = gearList.subList(54, 60);
                    List g11 = gearList.subList(60, 66);
                    List g12 = gearList.subList(66, 72);

                    List<List> listOfGears = new ArrayList<>(Arrays.asList(g1, g2, g3, g4, g5, g6, g7, g8, g9, g10, g11, g12));

                    for (int j=0; j < 11; j++){
                        List<Integer> gearLevelValues = (List) listOfGears.get(j).stream().map((gear) -> {
                            //System.out.println(link.attr("title"));
                            return gearValueMap.getOrDefault(gear, 0);
                        }).collect(toList());

                        outputList.add(Integer.toString(gearLevelValues.stream().mapToInt(Integer::intValue).sum()));
                        //System.out.println(gearLevelValues.stream().mapToInt(Integer::intValue).sum());
                    }

                    writer.writeNext(outputList.toArray(new String[outputList.size()]));


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
