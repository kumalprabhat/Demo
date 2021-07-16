package com.example.demo;

public class IncidentManagement {
    public String apps_impacted;
    public String user_impacted;
    public String finance_impacted;
    public String reputation_damage;
    public String dt_created;
    public Issue issue;
    public IncidentText incidentText;
    public String sla;
    public String tat_missed;

    IncidentManagement(){}

    public IncidentManagement(String apps_impacted, String user_impacted, String finance_impacted, String reputation_damage, String dt_created, Issue issue, IncidentText incidentText, String sla, String tat_missed) {
        this.apps_impacted = apps_impacted;
        this.user_impacted = user_impacted;
        this.finance_impacted = finance_impacted;
        this.reputation_damage = reputation_damage;
        this.dt_created = dt_created;
        this.issue = issue;
        this.incidentText = incidentText;
        this.sla = sla;
        this.tat_missed = tat_missed;
    }

    public String getApps_impacted() {
        return apps_impacted;
    }

    public String getUser_impacted() {
        return user_impacted;
    }

    public String getFinance_impacted() {
        return finance_impacted;
    }

    public String getReputation_damage() {
        return reputation_damage;
    }

    public String getDt_created() {
        return dt_created;
    }

    public Issue getIssue() {
        return issue;
    }

    public IncidentText getIncidentText() {
        return incidentText;
    }

    public String getSla() {
        return sla;
    }

    public String getTat_missed() {
        return tat_missed;
    }

    public void setApps_impacted(String apps_impacted) {
        this.apps_impacted = apps_impacted;
    }

    public void setUser_impacted(String user_impacted) {
        this.user_impacted = user_impacted;
    }

    public void setFinance_impacted(String finance_impacted) {
        this.finance_impacted = finance_impacted;
    }

    public void setReputation_damage(String reputation_damage) {
        this.reputation_damage = reputation_damage;
    }

    public void setDt_created(String dt_created) {
        this.dt_created = dt_created;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public void setIncidentText(IncidentText incidentText) {
        this.incidentText = incidentText;
    }

    public void setSla(String sla) {
        this.sla = sla;
    }

    public void setTat_missed(String tat_missed) {
        this.tat_missed = tat_missed;
    }
}
