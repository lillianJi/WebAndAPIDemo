package com.Common;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "ProjectId",
        "ProjectName",
        "Environment",
        "Platform",
        "TotalTestCount",
        "PassTestCount",
        "FailTestCount",
        "RunStatus",
        "StartTime",
        "RunDuration"
})
public class ReportTestResult {

    @JsonProperty("ProjectId")
    private String projectId;
    @JsonProperty("ProjectName")
    private String projectName;
    @JsonProperty("Environment")
    private String environment;
    @JsonProperty("Platform")
    private String platform;
    @JsonProperty("TotalTestCount")
    private Integer totalTestCount;
    @JsonProperty("PassTestCount")
    private Integer passTestCount;
    @JsonProperty("FailTestCount")
    private Integer failTestCount;
    @JsonProperty("RunStatus")
    private String runStatus;
    @JsonProperty("StartTime")
    private String startTime;
    @JsonProperty("RunDuration")
    private long runDuration;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ProjectId")
    public String getProjectId() {
        return projectId;
    }

    @JsonProperty("ProjectId")
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @JsonProperty("ProjectName")
    public String getProjectName() {
        return projectName;
    }

    @JsonProperty("ProjectName")
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @JsonProperty("Environment")
    public String getEnvironment() {
        return environment;
    }

    @JsonProperty("Environment")
    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    @JsonProperty("Platform")
    public String getPlatform() {
        return platform;
    }

    @JsonProperty("Platform")
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @JsonProperty("TotalTestCount")
    public Integer getTotalTestCount() {
        return totalTestCount;
    }

    @JsonProperty("TotalTestCount")
    public void setTotalTestCount(Integer totalTestCount) {
        this.totalTestCount = totalTestCount;
    }

    @JsonProperty("PassTestCount")
    public Integer getPassTestCount() {
        return passTestCount;
    }

    @JsonProperty("PassTestCount")
    public void setPassTestCount(Integer passTestCount) {
        this.passTestCount = passTestCount;
    }

    @JsonProperty("FailTestCount")
    public Integer getFailTestCount() {
        return failTestCount;
    }

    @JsonProperty("FailTestCount")
    public void setFailTestCount(Integer failTestCount) {
        this.failTestCount = failTestCount;
    }

    @JsonProperty("RunStatus")
    public String getRunStatus() {
        return runStatus;
    }

    @JsonProperty("RunStatus")
    public void setRunStatus(String runStatus) {
        this.runStatus = runStatus;
    }

    @JsonProperty("StartTime")
    public String getStartTime() {
        return startTime;
    }

    @JsonProperty("StartTime")
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @JsonProperty("RunDuration")
    public long getRunDuration() {
        return runDuration;
    }

    @JsonProperty("RunDuration")
    public void setRunDuration(long runDuration) {
        this.runDuration = runDuration;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}