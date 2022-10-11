import java.util.Objects;

/**
 * 
 * @author Dre, Smail 
 * Represents a person. A person has a name. 
 */
public class Person
{
	//Fields
	private String name;
	
	//Constructors
	
	/**
	 * Default constructor for a Person
	 */
	public Person(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	/**
	 * @param Object other
	 * @return boolean 
	 */
	@Override
	public final boolean equals(Object other)
	{
		if(this == other)
		{
			return true;
		}
		
		if(other == null)
		{
			return false;
		}
		
		if(other instanceof Artist)
		{
			Person person = (Person) other;
			return Objects.equals(getName(), person.getName());
		}
		return false;
	}
}
