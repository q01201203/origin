package com.origin.core.service;

import com.origin.data.entity.IAppUserTask;
import java.util.List;

public interface AppUserTaskService {

    void save(IAppUserTask appUserTask);
    void delete(Integer id);
    void update(IAppUserTask appUserTask);
    IAppUserTask findById(Integer id);
    List<IAppUserTask> find(IAppUserTask appUserTask);
    //add lc 170525
    List<IAppUserTask> findTaskUserInfo(IAppUserTask appUserTask);

    //add lc 170526
    void updateTaskSuccess(IAppUserTask appUserTask);
}