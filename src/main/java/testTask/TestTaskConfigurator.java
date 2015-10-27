package testTask;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.atlassian.bamboo.collections.ActionParametersMap;
import com.atlassian.bamboo.task.TaskConfigurator;
import com.atlassian.bamboo.task.TaskConfiguratorHelper;
import com.atlassian.bamboo.task.TaskDefinition;
import com.atlassian.bamboo.user.BambooAuthenticationContext;
import com.atlassian.bamboo.utils.error.ErrorCollection;
import com.atlassian.struts.TextProvider;
import com.google.common.collect.Maps;

/**
 * @author scott
 *
 */
public class TestTaskConfigurator
    implements TaskConfigurator
{

  private TextProvider textProvider;
  protected TaskConfiguratorHelper taskConfiguratorHelper;
  protected BambooAuthenticationContext bambooAuthenticationContext;

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
    context.put("say", "Hello World!");
  }

  /**
   * @see com.atlassian.bamboo.task.AbstractTaskConfigurator#populateContextForEdit(java.util.Map, com.atlassian.bamboo.task.TaskDefinition)
   */
  @Override
  public void populateContextForEdit(Map<String, Object> context, TaskDefinition taskDefinition)
  {
    context.put("say", taskDefinition.getConfiguration().get("say"));
  }

  /**
   * @see com.atlassian.bamboo.task.AbstractTaskConfigurator#populateContextForView(java.util.Map, com.atlassian.bamboo.task.TaskDefinition)
   */
  @Override
  public void populateContextForView(Map<String, Object> context, TaskDefinition taskDefinition)
  {
    context.put("say", taskDefinition.getConfiguration().get("say"));
  }

  /**
   * @see com.atlassian.bamboo.task.AbstractTaskConfigurator#validate(com.atlassian.bamboo.collections.ActionParametersMap, com.atlassian.bamboo.utils.error.ErrorCollection)
   */
  @Override
  public void validate(ActionParametersMap params, ErrorCollection errorCollection)
  {
    final String sayValue = params.getString("say");
    if (StringUtils.isEmpty(sayValue)) {
      errorCollection.addError("say", textProvider.getText("testtask.say.error"));
    }
  }

  /**
   * @see com.atlassian.bamboo.task.TaskConfigurator#generateTaskConfigMap(com.atlassian.bamboo.collections.ActionParametersMap, com.atlassian.bamboo.task.TaskDefinition)
   */
  @Override
  public Map<String, String> generateTaskConfigMap(@NotNull
  final ActionParametersMap params, @Nullable
  final TaskDefinition previousTaskDefinition)
  {
    Map<String, String> retVal = Maps.newHashMap();
    retVal.put("say", params.getString("say"));
    return retVal;
  }

  public void setTaskConfiguratorHelper(TaskConfiguratorHelper taskConfiguratorHelper)
  {
    this.taskConfiguratorHelper = taskConfiguratorHelper;
  }

  public void setAuthenticationContext(BambooAuthenticationContext bambooAuthenticationContext)
  {
    this.bambooAuthenticationContext = bambooAuthenticationContext;
  }
}
