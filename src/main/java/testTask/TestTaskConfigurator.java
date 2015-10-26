/*
 *  Copyright (C) 2001-2015 Vestmark, Inc. All rights reserved.
 *  THIS PROGRAM IS AN UNPUBLISHED WORK AND IS CONSIDERED A TRADE SECRET AND
 *  CONFIDENTIAL INFORMATION BELONGING TO VESTMARK, INC.
 *  ANY UNAUTHORIZED USE IS STRICTLY PROHIBITED.
 */

package testTask;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.atlassian.bamboo.collections.ActionParametersMap;
import com.atlassian.bamboo.task.AbstractTaskConfigurator;
import com.atlassian.bamboo.task.TaskDefinition;
import com.atlassian.bamboo.utils.error.ErrorCollection;
import com.opensymphony.xwork2.TextProvider;

/**
 * @author scott
 *
 */
public class TestTaskConfigurator
    extends AbstractTaskConfigurator
{

  private TextProvider textProvider;

  /**
   * 
   */
  public TestTaskConfigurator()
  {
  }

  /**
   * @return the textProvider
   */
  public TextProvider getTextProvider()
  {
    return textProvider;
  }

  /**
   * @param textProvider the textProvider to set
   */
  public void setTextProvider(TextProvider textProvider)
  {
    this.textProvider = textProvider;
  }

  /**
   * @see com.atlassian.bamboo.task.AbstractTaskConfigurator#populateContextForCreate(java.util.Map)
   */
  @Override
  public void populateContextForCreate(Map<String, Object> context)
  {
    super.populateContextForCreate(context);
    context.put("say", "Hello World!");
  }

  /**
   * @see com.atlassian.bamboo.task.AbstractTaskConfigurator#populateContextForEdit(java.util.Map, com.atlassian.bamboo.task.TaskDefinition)
   */
  @Override
  public void populateContextForEdit(Map<String, Object> context, TaskDefinition taskDefinition)
  {
    super.populateContextForEdit(context, taskDefinition);
    context.put("say", taskDefinition.getConfiguration().get("say"));
  }

  /**
   * @see com.atlassian.bamboo.task.AbstractTaskConfigurator#populateContextForView(java.util.Map, com.atlassian.bamboo.task.TaskDefinition)
   */
  @Override
  public void populateContextForView(Map<String, Object> context, TaskDefinition taskDefinition)
  {
    super.populateContextForView(context, taskDefinition);
    context.put("say", taskDefinition.getConfiguration().get("say"));
  }

  /**
   * @see com.atlassian.bamboo.task.AbstractTaskConfigurator#validate(com.atlassian.bamboo.collections.ActionParametersMap, com.atlassian.bamboo.utils.error.ErrorCollection)
   */
  @Override
  public void validate(ActionParametersMap params, ErrorCollection errorCollection)
  {
    super.validate(params, errorCollection);

    final String sayValue = params.getString("say");
    if (StringUtils.isEmpty(sayValue)) {
      errorCollection.addError("say", textProvider.getText("testtask.say.error"));
    }
  }
}
