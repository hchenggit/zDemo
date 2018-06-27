package my.study.test.deault;


public enum SearchColumn {
	START_TIME("startTime"),
	END_TIME("endTime"),
	ORG_ID("orgId"),
	SIMILARITY("similarity"),
	PIC_URL("picUrl"),
	RESNUM("resNum"),
	FEATURE("feature"),
	CAMERA_LIST("cameraList"),
	CAPTURE_TIME("capture_time"),
	CAPTURE_RESOURCE_ID("capture_resource_id"),
	FEATURE_TYPE("feature_type");
	
	private String des;
	SearchColumn(String des){
		this.des=des;
	}
	public String getSearchDes(SearchColumn c){
		return c.getDes();
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	
}
