package concurrency;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author Rob
 */
public class Concurrency
{

	public final static int ExitCode = 5;
	public final static int GetTotalPopulationCode = 3;
	//public final static int PrintCountiesCode = 5;
	public final static int LocationPopulation = 1;
	public final static int AverageReturnDataCode = 4;
	public final static int PrintTaskTimesCode = 2;

	/**
	 * @param args the command line arguments
	 * @throws java.io.IOException
	 */
	@SuppressWarnings("empty-statement")
	public static void main(String[] args) throws IOException
	{
		String filePath = "assets/population2016CSO.csv";

		ArrayList<String> counties = new ArrayList<>();

		ArrayList<PopulationDataRecord> DataSource = ParseFile(filePath, counties);
		ArrayList<LocationPopulationCountingTask> populationTaskList = new ArrayList<>();

		for (String county : counties)
		{
			LocationPopulationCountingTask locationPopulationCountingTask = Build(county, DataSource);
			populationTaskList.add(locationPopulationCountingTask);
			locationPopulationCountingTask.run();

		}
		Scanner keyboardScanner = new Scanner(System.in);

//
		int userMenuSelection = 0;
		do
		{
			PrintMenu();

			userMenuSelection = keyboardScanner.nextInt();
			switch (userMenuSelection)
			{
				case LocationPopulation:

					break;

				case AverageReturnDataCode:
					AverageReturnData(populationTaskList);
					break;

				case PrintTaskTimesCode:
					for (LocationPopulationCountingTask task : populationTaskList)
					{
						System.out.println(task.toString());
					}
					break;

				case GetTotalPopulationCode:
					GetTotalPopulation(populationTaskList);
					break;

//				case PrintCountiesCode:
//					System.out.println(counties.size());
//					for (String county : counties)
//					{
//						System.out.println(county);
//					}
//					break;
				case ExitCode:
					System.out.println("Goodbye");
					break;
			}
		} while (userMenuSelection != ExitCode);
	}

	private static void PrintMenu()
	{
		//System.out.println(LocationPopulation + ". Total population for each area regardless of age.");
		//System.out.println(PrintTaskTimesCode + ". Time in milliseconds it takes to complete the above concurrent task.");
		System.out.println(GetTotalPopulationCode + ". Calculate and display the population of Ireland.");
		System.out.println(AverageReturnDataCode + ". Average first and last county to return data.");
		System.out.println(ExitCode + ". Exit Application");
	}

	private static LocationPopulationCountingTask Build(String county, ArrayList<PopulationDataRecord> DataSource)
	{
		LocationPopulationCountingTask populationTask = new LocationPopulationCountingTask();
		populationTask.DataSource = DataSource;
		populationTask.searchCounty = county;

		return populationTask;
	}

	private static ArrayList<PopulationDataRecord> ParseFile(String filePath, ArrayList<String> counties) throws FileNotFoundException, IOException
	{
		ArrayList<PopulationDataRecord> trendList = new ArrayList<>();

		FileReader fileReader = new FileReader(filePath);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		String line;
		while ((line = bufferedReader.readLine()) != null)
		{
			String[] temp = line.split(",");
			String sex = temp[0];
			String ageRange = temp[1];
			String county = temp[2];
			String population = temp[3];
			trendList.add(new PopulationDataRecord(sex, ageRange, county, Integer.parseInt(population)));

			if (!counties.contains(county))
			{
				counties.add(county);
			}
		}
		bufferedReader.close();
		return trendList;

	}

	private static void GetTotalPopulation(ArrayList<LocationPopulationCountingTask> populationTaskList)
	{
		int total = 0;
		for (LocationPopulationCountingTask task : populationTaskList)
		{
			total += task.total;
		}
		System.out.println("Total Population:" + total);
	}

	private static void AverageReturnData(ArrayList<LocationPopulationCountingTask> populationTaskList)
	{
		final int times = 10;
		String[] winner = new String[times];
		String[] loser = new String[times];
		for (int i = 0; i < times; i++)
		{
//Future<>

			//Arrays.sort(winner, Comparator.comparingInt(String::length));
//			System.out.println("Last element is: "
//                               + populationTaskList.get(populationTaskList.size() - 1)); 
			//winner = populationTaskList[0];
			winner[i] = populationTaskList.get(0).searchCounty;
			loser[i] = populationTaskList.get(populationTaskList.size() - 1).searchCounty;

			for (LocationPopulationCountingTask task : populationTaskList)
			{

				task.run();
				System.out.println(task.toString());
				System.out.println("First element is: " + winner[i]);
				System.out.println("Last element is: " + loser[i]);
			}

			//		populationTaskList. sort list by endtime
			//java67.com/2015/06/how-to-get-first-and-last-elements-form-ArrayList-java.html
			//https://stackoverflow.com/questions/21970719/java-arrays-sort-with-lambda-expression
			//winner[i]=populationTaskList.first().searchCounty;
			//loser[i]]=populationTaskList.last().searchCounty;;
			//
		}
	}

}
