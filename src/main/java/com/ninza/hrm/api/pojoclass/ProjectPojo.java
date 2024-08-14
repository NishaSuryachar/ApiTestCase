package com.ninza.hrm.api.pojoclass;

public class ProjectPojo {

	String projectName;
	String status;
	String CreatedBy;
	int teamsize;
	
	public ProjectPojo(String projectName, String status, String createdBy, int teamsize) {
		super();
		this.projectName = projectName;
		this.status = status;
		this.CreatedBy = createdBy;
		this.teamsize = teamsize;
	}
	public ProjectPojo(String status, String createdBy, int teamsize) {
		super();
		this.status = status;
		this.CreatedBy = createdBy;
		this.teamsize = teamsize;
	}
	ProjectPojo()
    {}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreatedBy() {
		return CreatedBy;
	}
	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}
	public int getTeamsize() {
		return teamsize;
	}
	public void setTeamsize(int teamsize) {
		this.teamsize = teamsize;
	}
	
	
}
