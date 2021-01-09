package DBTable;

interface Veri {
	
	int getTNumber();
	String tableName();
}

abstract class VeriTabani implements Veri {

	private final int num;

	protected VeriTabani(int tableNumber) {
		this.num = tableNumber;
	}

	public int getTNumber() {
		return this.num;
	}
}

class ClinicTable extends VeriTabani {

	protected ClinicTable(int tableNumber) {
		super(tableNumber);
	}

	@Override
	public String tableName() {
		return "clinic";
	}
}

class RandevuTable extends VeriTabani {

	protected RandevuTable(int tableNumber) {
		super(tableNumber);
	}

	@Override
	public String tableName() {
		return "randevu";
	}
}

class UserTable extends VeriTabani {

	protected UserTable(int tableNumber) {
		super(tableNumber);
	}

	@Override
	public String tableName() {
		return "user";
	}
}

class WhourTable extends VeriTabani {

	protected WhourTable(int tableNumber) {
		super(tableNumber);
	}

	@Override
	public String tableName() {
		return "whour";
	}
}

class WorkerTable extends VeriTabani {

	protected WorkerTable(int tableNumber) {
		super(tableNumber);
	}

	@Override
	public String tableName() {
		return "worker";
	}
}

public class Table {
	public static void main(String[] args) {
		VeriTabani[] tables = { new ClinicTable(1), 
								new RandevuTable(2),
								new UserTable(3), 
								new WhourTable(4),
								new WorkerTable(5) };

		System.out.println("Tablo Numarasý  Tablo Adý");
		for (VeriTabani t : tables) {
			System.out.println(t.getTNumber() + " .Tablo       : " + t.tableName());
		}
	}
}
