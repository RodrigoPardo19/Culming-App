package com.ufro.culmingapp.subject.domain;

import javax.persistence.Embeddable;

import com.ufro.culmingapp.shared.domain.exceptions.NullFieldNotPermitted;
import com.ufro.culmingapp.subject.domain.exceptions.NumberOfQuotasNotValid;

@Embeddable
public class SubjectQuota {

    private Integer quotas;

    public SubjectQuota() {
        // Used only for spring
    }

    public SubjectQuota(Integer quotas) throws NullFieldNotPermitted, NumberOfQuotasNotValid {
        if (isNull(quotas)) {
            throw new NullFieldNotPermitted("Quotas");
        }
        if (!isValid(quotas)) {
            throw new NumberOfQuotasNotValid();
        }
        this.quotas = quotas;
    }

    public Boolean isNull(Integer quotas) {
        return quotas == null;
    }

    public Boolean isValid(Integer quotas) {
        if (quotas < 0 || quotas > 150) {
            return false;
        }
        return true;
    }

    public Integer getQuotas() {
        return this.quotas;
    }

}
