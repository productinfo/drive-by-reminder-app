package at.fhj.itm10.mobcomp.drivebyreminder.models;

/**
 * Special location result which stores the location
 * and some of the associated task data.
 * 
 * @author Wolfgang Gaar
 */
public class BoundedLocation {

	private long taskId;
	
	private int customProximitry;
	
	private String title;
	
	private String description;
	
	private double latitude;
	
	private double longitude;

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public int getCustomProximitry() {
		return customProximitry;
	}

	public void setCustomProximitry(int customProximitry) {
		this.customProximitry = customProximitry;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "BoundedLocation [taskId=" + taskId + ", customProximitry="
				+ customProximitry + ", title=" + title + ", latitude="
				+ latitude + ", longitude=" + longitude + "]";
	}

}
