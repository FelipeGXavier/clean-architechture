package com.demo.clean.accounting.application;

import com.demo.clean.accounting.adapter.DashboardResponse;

public interface UserInformation {

    DashboardResponse execute(Long personId);
}
