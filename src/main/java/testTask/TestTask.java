package testTask;

import org.springframework.util.Assert;

import com.atlassian.bamboo.build.logger.BuildLogger;
import com.atlassian.bamboo.build.test.TestCollationService;
import com.atlassian.bamboo.process.EnvironmentVariableAccessor;
import com.atlassian.bamboo.process.ProcessService;
import com.atlassian.bamboo.task.TaskContext;
import com.atlassian.bamboo.task.TaskException;
import com.atlassian.bamboo.task.TaskResult;
import com.atlassian.bamboo.task.TaskResultBuilder;
import com.atlassian.bamboo.task.TaskType;
import com.atlassian.bamboo.v2.build.agent.capability.CapabilityContext;

/**
 * @author scott
 *
 */
public class TestTask
    implements TaskType
{

  private final CapabilityContext capabilityContext;
  private final EnvironmentVariableAccessor environmentVariableAccessor;
  private final ProcessService processService;
  private final TestCollationService testCollationService;

  /**
   * @param capabilityContext
   * @param environmentVariableAccessor
   * @param processService
   * @param testCollationService
   */
  public TestTask(
      CapabilityContext capabilityContext,
      EnvironmentVariableAccessor environmentVariableAccessor,
      ProcessService processService,
      TestCollationService testCollationService)
  {
    this.capabilityContext = capabilityContext;
    this.environmentVariableAccessor = environmentVariableAccessor;
    this.processService = processService;
    this.testCollationService = testCollationService;
    Assert.notNull(this.capabilityContext, "capabilityContext must not be null.");
    Assert.notNull(this.environmentVariableAccessor, "environmentVariableAccessor must not be null.");
    Assert.notNull(this.processService, "processService must not be null.");
    Assert.notNull(this.testCollationService, "testCollationService must not be null.");
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
