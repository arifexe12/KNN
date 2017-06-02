import java.util.HashMap;
import java.util.LinkedList;

public class DataSet {
	private HashMap<String, String> data_set = null;
	private LinkedList<String> titles = null;
	private double distace = 0d;

	public DataSet() {
		data_set = new HashMap<String, String>();
		titles = new LinkedList<String>();

	}

	public void setDistace(double distace) {
		this.distace = distace;
	}

	public double getDistace() {
		return distace;
	}

	public void setTitles(String[] titles) {
		for (String string : titles) {
			this.titles.add(string);
		}
	}

	public LinkedList<String> getTitles() {
		return titles;
	}

	public void setDataTuples(String[] inputs) {
		for (int i = 0; i < inputs.length; i++) {
			data_set.put(titles.get(i), inputs[i]);
		}
	}

	String getValueForKey(String key) {
		return data_set.get(key);
	}

	@Override
	public String toString() {
		String output = "";

		for (String string : data_set.keySet()) {
			output = output + string + "=" + data_set.get(string) + ",";
		}
		output = output.substring(0, output.length() - 1);
		return output;
	}

	public HashMap<String, String> getDataTuples() {
		return data_set;
	}

}
