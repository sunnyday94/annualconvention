package com.chunmi.annualconvention.po;

public class JobNums {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cm_job_num.id
     *
     * @mbg.generated Wed Dec 27 13:13:48 CST 2017
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cm_job_num.job_num
     *
     * @mbg.generated Wed Dec 27 13:13:48 CST 2017
     */
    private String jobNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cm_job_num.del_flag
     *
     * @mbg.generated Wed Dec 27 13:13:48 CST 2017
     */
    private String delFlag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cm_job_num.id
     *
     * @return the value of cm_job_num.id
     *
     * @mbg.generated Wed Dec 27 13:13:48 CST 2017
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cm_job_num.id
     *
     * @param id the value for cm_job_num.id
     *
     * @mbg.generated Wed Dec 27 13:13:48 CST 2017
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cm_job_num.job_num
     *
     * @return the value of cm_job_num.job_num
     *
     * @mbg.generated Wed Dec 27 13:13:48 CST 2017
     */
    public String getJobNum() {
        return jobNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cm_job_num.job_num
     *
     * @param jobNum the value for cm_job_num.job_num
     *
     * @mbg.generated Wed Dec 27 13:13:48 CST 2017
     */
    public void setJobNum(String jobNum) {
        this.jobNum = jobNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cm_job_num.del_flag
     *
     * @return the value of cm_job_num.del_flag
     *
     * @mbg.generated Wed Dec 27 13:13:48 CST 2017
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cm_job_num.del_flag
     *
     * @param delFlag the value for cm_job_num.del_flag
     *
     * @mbg.generated Wed Dec 27 13:13:48 CST 2017
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}