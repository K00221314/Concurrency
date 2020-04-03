/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrency;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Rob
 */
public class Concurrency implements Runnable
{
	public final static int ExitCode = 4;
	public final static int PrintCountiesCode = 5;

	/**
	 * @param args the command line arguments
	 * @throws java.io.IOException
	 */
	@SuppressWarnings("empty-statement")
	public static void main(String[] args) throws IOException
	{
		ArrayList<String> counties = new ArrayList<String>();

		Object DataSource = locateFile(counties);
		ArrayList<Concurrency> runnerList = new ArrayList<>();

		for(String county : counties)
		{
			Concurrency runner = Build(county, DataSource);
			runnerList.add(runner);
			runner.run();

		}

//	boolean data = true;
//		String text = "";
//		try
//		{
//			File file = new File("assets/population2016CSO.csv");
//
//			if (!file.exists())
//			{
//				System.out.println("cannot find file");
//			} else
//			{
//				FileInputStream fileInputStream = new FileInputStream(file);
//				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//
//				while (data)
//				{
//					try
//					{
//						Object object = (Object) objectInputStream.readObject();
//
//
//
//					} catch (EOFException end)
//					{
//						data = false;
//					}
//
//				}
//			}
//		} catch (Exception ex)
//		{
//			System.out.println("Problem reading from file" + ex);
//		}
//		{
//
//		}
		Scanner s = new Scanner(System.in);

//
		int option = 0;
		do
		{
			System.out.println("1. Total population for each area regardless of age");
			System.out.println("2. Time in milliseconds it takes to complete the above concurrent task.");
			System.out.println("3. Calculate and display the population of Ireland based on the information returned form the concurrent tasks above");
			System.out.println("4. ");

			option = s.nextInt();
			switch(option)
			{
				case 1:
//					BlockingDeque<String> deque = new LinkedBlockingDeque<String>();
//					deque.addFirst("population");
//					deque.addLast("age");
//
//				String two = deque.takeLast("population");
//				String one = deque.takeFirst();
//

					break;

				case 2:

					break;

				case 3:

					break;

				case PrintCountiesCode:
					for(String county : counties)
					{
						System.out.println(county);

					}
					break;
				case ExitCode:

					break;

			}

		}
		while(option != ExitCode);
	}

	private static ArrayList<Population> locateFile(ArrayList<String> counties) throws FileNotFoundException, IOException
	{
		ArrayList<Population> trendList = new ArrayList<>();
		String fileIn = "assets/population2016CSO.csv";

		String line = null;
//            Population transaction = new Population();

		FileReader fileReader = new FileReader(fileIn);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		while((line = bufferedReader.readLine()) != null)
		{

			String[] temp = line.split(",");
			String sex = temp[0];
			String age = temp[1];
			String county = temp[2];
			String population = temp[3];
			trendList.add(new Population(sex, age, county, Integer.parseInt(population)));

			if(!counties.contains(county))
			{
				counties.add(county);
			}
//			System.out.println("sex " + sex);
//			System.out.println("age  " + age);
//			System.out.println("county  " + county);
//			System.out.println("population  " + population);
		}
		bufferedReader.close();
		return trendList;

	}

	private static Concurrency Build(String county, Object DataSource)
	{
		Concurrency runnable = new Concurrency();
		runnable.DataSource = DataSource;
		runnable.searchCounty = county;

		return runnable;
	}

	String searchCounty;
	Object DataSource;
	long timeTaken = 0;

	@Override
	public void run()
	{
		long start = System.nanoTime();
		int total = 0;
		//
		//	going throught the file
		//
		System.out.println(searchCounty + " " + total);
		timeTaken = System.nanoTime() - start;
		System.out.printf("Task took %.3f ms to run%n", timeTaken / 1e6);

	}

}
