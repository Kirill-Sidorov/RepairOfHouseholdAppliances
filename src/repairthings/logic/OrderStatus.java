package repairthings.logic;

public enum OrderStatus {
	CHECK_BY_MODERATOR {
		{
			this.id = 1;
			this.name = "Проверка модаратора";
		}
	},
	WAIT_MASTER {
		{
			this.id = 2;
			this.name = "Ожидает мастера";
		}
	},
	CREATION_PARTS_LIST {
		{
			this.id = 3;
			this.name = "Составление списка з/ч";
		}
	},
	WAIT_REPLACEMENT_PARTS {
		{
			this.id = 4;
			this.name ="Ожидает поступления з/ч";
		}
	},
	REPAIR_PROCESS {
		{
			this.id = 5;
			this.name ="Процесс ремонта";
		}
	},
	COMPLETED {
		{
			this.id = 6;
			this.name = "Завершен";
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
