package cn.edu.nenu.clzc.commons.entites.teacher;

import java.util.Date;

public class TeacherExamination {
    private String id;

    private String unitId;

    private Date examinationPersistTime;

    private String examinationCreateUsername;

    private String examinationType;

    private Date examinationTime;

    private String examinationIsDelete;

    private String examinationInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public Date getExaminationPersistTime() {
        return examinationPersistTime;
    }

    public void setExaminationPersistTime(Date examinationPersistTime) {
        this.examinationPersistTime = examinationPersistTime;
    }

    public String getExaminationCreateUsername() {
        return examinationCreateUsername;
    }

    public void setExaminationCreateUsername(String examinationCreateUsername) {
        this.examinationCreateUsername = examinationCreateUsername;
    }

    public String getExaminationType() {
        return examinationType;
    }

    public void setExaminationType(String examinationType) {
        this.examinationType = examinationType;
    }

    public Date getExaminationTime() {
        return examinationTime;
    }

    public void setExaminationTime(Date examinationTime) {
        this.examinationTime = examinationTime;
    }

    public String getExaminationIsDelete() {
        return examinationIsDelete;
    }

    public void setExaminationIsDelete(String examinationIsDelete) {
        this.examinationIsDelete = examinationIsDelete;
    }

    public String getExaminationInfo() {
        return examinationInfo;
    }

    public void setExaminationInfo(String examinationInfo) {
        this.examinationInfo = examinationInfo;
    }
}