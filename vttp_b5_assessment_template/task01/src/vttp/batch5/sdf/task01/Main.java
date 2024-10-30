package vttp.batch5.sdf.task01;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import vttp.batch5.sdf.task01.models.BikeEntry;

// Use this class as the entry point of your program

public class Main {

	public static void main(String[] args) throws IOException {

		// Initialise file
		File f = new File("vttp_b5_assessment_template\\task01\\day.csv");
		
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);

		List<BikeEntry>BikeData = new ArrayList<>();
        String[] headersArr;
      
        // store headers in an array
        String headers = br.readLine();
        String[] headerArr = headers.split(",");
      

		// store each row as an instance of BikeEntry
		String line;
        while((line= br.readLine())!=null){
            String[] dataArray = line.split(",");
			BikeData.add(BikeEntry.toBikeEntry(dataArray));
        }
		
		br.close();
		fr.close();

		List<Integer> sum = BikeData.stream()
										.map(t -> t.getCasual()+ t.getRegistered())
										.sorted(Collections.reverseOrder())
										.collect(Collectors.toList());
		//System.out.println(total);

		String[]position = {"highest","second highest","third highest", "fourth highest","fifth highest"};
		String[]season = new String[5];
		String[] day = new String[5];
		String[] month = new String[5];
		//int[] total = {sum.get(0),sum.get(1),sum.get(2),sum.get(3),sum.get(4)};
		List<Integer> total = new ArrayList<>();
		for (int i =0; i<5;i++){
			total.add(sum.get(i));
		}
		String[] weather = new String[5];
		String[] holiday = new String[5];

		String[] weatherDesc = {"Clear, Few clouds, Partly cloudy, Partly cloudy",
		"Mist + Cloudy, Mist + Broken clouds, Mist + Few clouds, Mist",
		"Light Snow, Light Rain + Thunderstorm + Scattered clouds, Light Rain + Scattered clouds",
		"Heavy Rain + Ice Pallets + Thunderstorm + Mist, Snow + Fog"};


		for (BikeEntry data: BikeData){
			
			int totalVal = (data.getCasual())+(data.getRegistered());
			if (total.contains(totalVal)){
				int index = total.indexOf(totalVal);
				season[index] = Utilities.toSeason(data.getSeason());
				day[index] = Utilities.toWeekday(data.getWeekday());
				month[index] = Utilities.toMonth(data.getMonth());
				int weatherInt = data.getWeather();
				if (weatherInt == 1){
					weather[index] = weatherDesc[0];
				}
				else if (weatherInt == 2){
					weather[index] = weatherDesc[0];
				}
				else if (weatherInt == 3){
					weather[index] = weatherDesc[0];
				}
				else if (weatherInt == 4){
					weather[index] = weatherDesc[0];
				}

				boolean holidayVal = data.isHoliday();
				if (holidayVal == true){
					holiday[index] = "a holiday";
				}
				else if(holidayVal == false){
					holiday[index] = "not a holiday";
				}
			}
			
		}
		for(int j = 0; j<5 ; j++){
			System.out.printf("The %s recorded number of cyclists was in %s, on a %s in the month of %s.\n",position[j],season[j],day[j],month[j]);
			System.out.printf("There were a total of %d cyclists.\n",total.get(j));
			System.out.printf("The weather was %s.\n",weather[j]);
			System.out.printf("%s was %s.\n\n",day[j],holiday[j]);

		}

		

	}
}
