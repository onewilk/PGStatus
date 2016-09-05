package it.wilk.pg.utils;

import java.text.Collator;
import java.text.RuleBasedCollator;
import java.util.Comparator;

import it.wilk.pg.model.StatusInfo;

/**
 * 排序比较器
 * Created by Mr.Wilk on 2016/08/12 0012.
 */
public class SortByNameAndStatus implements Comparator<StatusInfo> {
    @Override
    public int compare(StatusInfo s1, StatusInfo s2) {
        if (s1.isOpen == s2.isOpen) {
            RuleBasedCollator collator = (RuleBasedCollator) Collator.getInstance(java.util.Locale.ENGLISH);
            return collator.compare(collator.getCollationKey(s1.name).getSourceString(), collator.getCollationKey(s2.name).getSourceString());
        } else if (s1.isOpen) {
            return -1;
        } else {
            return 1;
        }
    }
}
