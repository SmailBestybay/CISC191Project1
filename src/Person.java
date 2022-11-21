import java.util.Objects;

/**
 * 
 * @author Dre, Smail 
 * Represents a person. A person has a name. 
 */
public abstract class Person
{
	//Fields
	private final String name;
	
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

	public abstract String toString();
	
	/**
	 * @param other object to compare
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
