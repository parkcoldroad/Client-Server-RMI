package entity;

public abstract class Domain {
	public abstract boolean match(String domainId);

	public abstract String getName();

	public abstract String getId();

	public abstract String toString();

	public abstract void showAttributes();
}
