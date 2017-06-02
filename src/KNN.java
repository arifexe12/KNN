import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

public class KNN {
	String input_file_path = null;
	LinkedList<DataSet> alls = null;
	HashMap<String, Integer> response_map = null;
	String input_tuple = null;
	String find = null;
	LinkedList<String> exclude = null;
	int k = 0;
	HashMap<String, String> tuple_input_map = null;

	public void setFind(String find) {
		this.find = find;
	}

	public String getFind() {
		return find;
	}

	public void setK(int k) {
		this.k = k;
	}

	public int getK() {
		return k;
	}

	public KNN(String input_path) {
		alls = new LinkedList<DataSet>();
		exclude = new LinkedList<String>();
		response_map = new HashMap<String, Integer>();
		tuple_input_map = new HashMap<String, String>();
		this.input_file_path = input_path;
		readDataSets();
	}

	void performKNN() {
		for (DataSet dataSet : alls) {
			double distance = getDistance(dataSet, tuple_input_map);
			dataSet.setDistace(distance);
		}
		Collections.sort(alls, new Comparator<DataSet>() {
			@Override
			public int compare(DataSet o1, DataSet o2) {

				return (int) (o1.getDistace() - o2.getDistace());
			}
		});
   printResult();
	}

	void printResult() {
		response_map.clear();
		System.out.println("Result :");
		for (int i = 0; i < getK(); i++) {
			DataSet ds = alls.get(i);
			String key = ds.getValueForKey(getFind());
			if (response_map.containsKey(key)) {
				response_map.put(key, (response_map.get(key) + 1));
			} else {
				response_map.put(key, 1);
			}
			System.out.println(ds.toString());
		}
		int dist = -1;
		String response = "";
		for (String res : response_map.keySet()) {
			if (dist < response_map.get(res)) {
				dist = response_map.get(res);
				response = res;
			}
		}
		System.out.println(getFind() + ":" + response);
	}

	private void populateTupleInputMap() {
		input_tuple = input_tuple.substring(1, input_tuple.length() - 1);
		String[] parts = input_tuple.split(",");
		for (String string : parts) {
			if (string.contains("?")) {
				find=string.split("=")[0];
				exclude.add(string.split("=")[0]);
			} else if (string.contains("!")) {
				exclude.add(string.split("=")[0]);
			} else {
				String[] values = string.split("=");
				tuple_input_map.put(values[0], values[1]);
			}
		}

	}

	double getDistance(DataSet d1, HashMap<String, String> input_tuple_map) {
		double distance = 0d;
		for (String key : tuple_input_map.keySet()) {
			distance = distance + Math.abs(
					Double.parseDouble(d1.getDataTuples().get(key)) - Double.parseDouble(input_tuple_map.get(key)));
		}
		return distance;
	}

	public void setInputTuple(String input_tuple) {
		this.input_tuple = input_tuple;
		populateTupleInputMap();
	}

	public void readDataSets() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.input_file_path));
			String line = null;
			String[] titles = null;
			if ((line = br.readLine()) != null) {
				if ((line = line.trim()).length() > 0) {
					titles = line.split("\\s{1,}");

				}
			}

			while ((line = br.readLine()) != null) {

				if ((line = line.trim()).length() > 0) {
					String[] parts = line.split("\\s{1,}");

					DataSet dataSet = new DataSet();
					dataSet.setTitles(titles);
					dataSet.setDataTuples(parts);
					alls.add(dataSet);

				}

			}

		} catch (Exception e) {

		}
	}
}
