package repairthings.logic;

public enum OrderStatus {
	CHECK_BY_MODERATOR {
		{
			this.id = 1;
			this.name = "�������� ����������";
		}
	},
	WAIT_MASTER {
		{
			this.id = 2;
			this.name = "������� �������";
		}
	},
	CREATION_PARTS_LIST {
		{
			this.id = 3;
			this.name = "����������� ������ �/�";
		}
	},
	WAIT_REPLACEMENT_PARTS {
		{
			this.id = 4;
			this.name ="������� ����������� �/�";
		}
	},
	REPAIR_PROCESS {
		{
			this.id = 5;
			this.name ="������� �������";
		}
	},
	COMPLETED {
		{
			this.id = 6;
			this.name = "��������";
		}
	};
	protected int id;
	protected String name;
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
}
