/*
 *  Copyright (C) 2001-2015 Vestmark, Inc. All rights reserved.
 *  THIS PROGRAM IS AN UNPUBLISHED WORK AND IS CONSIDERED A TRADE SECRET AND
 *  CONFIDENTIAL INFORMATION BELONGING TO VESTMARK, INC.
 *  ANY UNAUTHORIZED USE IS STRICTLY PROHIBITED.
 */

package testTask;

import com.atlassian.bamboo.build.logger.BuildLogger;
import com.atlassian.bamboo.task.TaskContext;
import com.atlassian.bamboo.task.TaskException;
import com.atlassian.bamboo.task.TaskResult;
import com.atlassian.bamboo.task.TaskResultBuilder;
import com.atlassian.bamboo.task.TaskType;

/**
 * @author scott
 *
 */
public class TestTask
    implements TaskType
{

  /**
   * 
   */
  public TestTask()
  {
  }

  /**
   * @see com.atlassian.bamboo.task.TaskType#execute(com.atlassian.bamboo.task.TaskContext)
   */
  @Override
  public TaskResult execute(TaskContext taskContext) throws TaskException
  {
    final BuildLogger buildLogger = taskContext.getBuildLogger();

    final String say = taskContext.getConfigurationMap().get("say");
    buildLogger.addBuildLogEntry(say);

    return TaskResultBuilder.newBuilder(taskContext).success().build();
  }

}