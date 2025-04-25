package org.eternity.step03;

public class SequenceCondition implements DiscountCondition {
    private DiscountConditionType type;

    private int sequence;

    public boolean isSatisfiedBy(Screening screening) {
        return sequence == screening.getSequence();
    }
}
