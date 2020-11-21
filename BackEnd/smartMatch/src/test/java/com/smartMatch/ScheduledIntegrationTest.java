package com.smartMatch;

import com.smartMatch.feedback.FeedbackDB;
import com.smartMatch.feedback.FeedbackScheduler;
import com.smartMatch.user.UserController;
import org.awaitility.Duration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ScheduledIntegrationTest
{

    @SpyBean
    private FeedbackScheduler task;
    @Autowired
    private UserController controller;

    @Test
    public void jobRuns()
    {
        await().atMost(Duration.ONE_MINUTE).untilAsserted(() -> verify(task, times(1)).checkFeedbacks());
    }

    @Test
    public void checkResultOfMails()
    {
        controller.saveFeedback(new FeedbackDB("data2", 2));
        controller.saveFeedback(new FeedbackDB("data3", 3));
        controller.saveFeedback(new FeedbackDB("data4", 4));
        List<FeedbackDB> list = controller.getAllFeedbacks();
        assertTrue((list.size() > 0));
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list = controller.getAllFeedbacks();
        assertTrue((list.size() == 0));
    }

    @Test
    public void checkQuickResultOfMails()
    {
        controller.saveFeedback(new FeedbackDB("data2", 2));
        List<FeedbackDB> list = controller.getAllFeedbacks();
        assertTrue((list.size() > 0) && (list.size() < 2));
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list = controller.getAllFeedbacks();
        assertTrue((list.size() == 0));
    }
}