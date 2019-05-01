package xyz.frt.basesdk2.entity;


import java.util.ArrayList;
import java.util.List;

public abstract class BaseExample {

    private String orderBy;

    private boolean distinct;

    private List<Clause> clauseList;

    public BaseExample() {
        clauseList = new ArrayList<>();
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Clause> getClauseList() {
        return clauseList;
    }

    public Clause or() {
        Clause clause = createClauseInternal();
        clauseList.add(clause);
        return clause;
    }

    public Clause createClause() {
        Clause clause = createClauseInternal();
        if (clauseList.size() == 0) {
            clauseList.add(clause);
        }
        return clause;
    }

    public void clear() {
        clauseList.clear();
        orderBy = null;
        distinct = false;
    }

    public Clause createClauseInternal() {
        return new Clause();
    }

    public static class Clause {

        private List<Block> blockList;

        public Clause() {
            this.blockList = new ArrayList<>();
        }

        public List<Block> getBlockList() {
            return blockList;
        }

        public boolean isValid() {
            return blockList.size() > 0;
        }

        public void addBlock(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            blockList.add(new Block(condition));
        }

        void addBlock(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            blockList.add(new Block(condition, value));
        }

        void addBlock(String condition, Object value, Object secondValue, String property) {
            if (value == null || secondValue == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            blockList.add(new Block(condition, value, secondValue));
        }

        void addBlock(String column, String operator, Object value) {
            if (value == null) {
                throw new RuntimeException("Value for " + column + " cannot be null");
            }
            blockList.add(new Block(column, operator, value));
        }

        void addBlock(String column, String operator, Object value, Object secondValue) {
            if (value == null || secondValue == null) {
                throw new RuntimeException("Between values for " + column + " cannot be null");
            }
            blockList.add(new Block(column, operator, value, secondValue));
        }

        public Clause withIdEqualTo(Object value) {
            addBlock("id = ", value, "id");
            return this;
        }

        public Clause withIdNotEqualTo(Object value) {
            addBlock("id <> ", value, "id");
            return this;
        }

        public Clause withIdIn(List<Object> values) {
            addBlock("id in ", values, "id");
            return this;
        }

        public Clause withIdNotIn(List<Object> values) {
            addBlock("id not in ", values, "id");
            return this;
        }

        public Clause withIdBetween(Object value, Object secondValue) {
            addBlock("id between ", value, secondValue, "id");
            return this;
        }

        public Clause withIdNotBetween(Object value, Object secondValue) {
            addBlock("id not between ", value, secondValue, "id");
            return this;
        }

        public Clause withIdLessThan(Object value) {
            addBlock("id < ", value, "id");
            return this;
        }

        public Clause withIdLessThanOrEqualTo(Object value) {
            addBlock("id <= ", value, "id");
            return this;
        }

        public Clause withIdGreaterThan(Object value) {
            addBlock("id > ", value, "id");
            return this;
        }

        public Clause withIdGreaterThanOrEqualTo(Object value) {
            addBlock("id >= ", value, "id");
            return this;
        }

        public Clause withColumnEqualTo(String column, Object value) {
            addBlock(column, "=", value);
            return this;
        }

        public Clause withColumnNotEqualTo(String column, Object value) {
            addBlock(column, "<>", value);
            return this;
        }


    }

    public static class Block {

        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        Block(String condition) {
            this.condition = condition;
            this.noValue = true;
        }

        Block(String condition, Object value) {
            this.condition = condition;
            this.value = value;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        Block(String condition, Object value, Object secondValue) {
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.betweenValue = true;
        }

        Block(String column, String operator, Object value) {
            this.condition = column + " " + operator;
            this.value = value;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        Block(String column, String operator, Object value, Object secondValue) {
            this.condition = column + " " + operator + " ";
            this.value = value;
            this.secondValue = secondValue;
            this.betweenValue = true;
        }
    }

}
