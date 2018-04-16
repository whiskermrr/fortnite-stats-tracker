package com.example.mrr.fortnitetracker.Model;

import java.util.List;
import java.util.Map;

public class UserProfileModel {

    private String accountId;
    private int platformId;
    private String platformName;
    private String platformNameLong;
    private String epicUserHandle;

    private Map<String, Map<String, UserStatsModel>> stats;
    private List<UserLifeTimeStats> lifeTimeStats;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public int getPlatformId() {
        return platformId;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getPlatformNameLong() {
        return platformNameLong;
    }

    public void setPlatformNameLong(String platformNameLong) {
        this.platformNameLong = platformNameLong;
    }

    public String getEpicUserHandle() {
        return epicUserHandle;
    }

    public void setEpicUserHandle(String epicUserHandle) {
        this.epicUserHandle = epicUserHandle;
    }

    public Map<String, Map<String, UserStatsModel>> getStats() {
        return stats;
    }

    public void setStats(Map<String, Map<String, UserStatsModel>> stats) {
        this.stats = stats;
    }

    public List<UserLifeTimeStats> getLifeTimeStats() {
        return lifeTimeStats;
    }

    public void setLifeTimeStats(List<UserLifeTimeStats> lifeTimeStats) {
        this.lifeTimeStats = lifeTimeStats;
    }

    public class UserStatsModel {

        private String label;
        private String field;
        private String category;
        private int valueInt;
        private double value;
        private int rank;
        private double percentile;
        private String displayValue;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public int getValueInt() {
            return valueInt;
        }

        public void setValueInt(int valueInt) {
            this.valueInt = valueInt;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public double getPercentile() {
            return percentile;
        }

        public void setPercentile(double percentile) {
            this.percentile = percentile;
        }

        public String getDisplayValue() {
            return displayValue;
        }

        public void setDisplayValue(String displayValue) {
            this.displayValue = displayValue;
        }
    }

    public class UserLifeTimeStats {
        private  String key;
        private  String value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}


