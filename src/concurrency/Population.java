/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrency;

/**
 *
 * @author Rob
 */
public class Population
{
	private String sex;
	private String age;
	private String county;
	private int population;

	Population(String sex, String age, String county, int population)
	{
		this.sex= sex;
		this.age= age;
		this.county = county;
		this.population = population;
	}

	Population(String sex, String age, String county, String population)
	{
		this.sex= sex;
		this.age= age;
		this.county = county;
		this.population = Integer.parseInt(population);
	}

	/**
	 * @return the sex
	 */
	public String getSex()
	{
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex)
	{
		this.sex = sex;
	}

	/**
	 * @return the age
	 */
	public String getAge()
	{
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(String age)
	{
		this.age = age;
	}

	/**
	 * @return the county
	 */
	public String getCounty()
	{
		return county;
	}

	/**
	 * @param county the county to set
	 */
	public void setCounty(String county)
	{
		this.county = county;
	}

	
	public int getPopulation()
	{
		return population;
	}

	
	/**
	 * @param population the population to set
	 */
	public void setPopulation(int population)
	{
		this.population = population;
	}
}
