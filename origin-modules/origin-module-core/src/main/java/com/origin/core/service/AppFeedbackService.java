package com.origin.core.service;

import com.origin.data.entity.IAppFeedback;
import java.util.List;

public interface AppFeedbackService {

    void save(IAppFeedback appFeedback);
    void delete(Integer id);
    void update(IAppFeedback appFeedback);
    IAppFeedback findById(Integer id);
    List<IAppFeedback> find(IAppFeedback appFeedback);
}