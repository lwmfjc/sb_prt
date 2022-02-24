package com.ly.dao;

import com.ly.model.vm.DemoDataVm;
import com.ly.model.vm.MaterialReportVm;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 假设这个是你的DAO存储。当然还要这个类让spring管理，当然你不用需要存储，也不需要这个类。
 **/
@Repository
public interface MaterialReportMapper {
    void batchInsert(List<MaterialReportVm> list);
    List<MaterialReportVm> findAll();
}
