import TaskCard from "../common/TaskCard.JSX";

const TasksSection = ({ taskArray, saveTask, triggerRefresh }) => {
  const tasksJSX = [...taskArray].map((task) => {
    return (
      <TaskCard
        key={task.id}
        taskId={task.id}
        title={task.title}
        description={task.description}
        dueDate={task.dueDate}
        completed={task.completed}
        saveTask={saveTask}
        triggerRefresh={triggerRefresh}
      />
    );
  });

  return (
    <main className="dashboard">
      <h2 id="dashboard-heading">Dashboard</h2>
      <div className="tasks-section">{tasksJSX}</div>
    </main>
  );
};

export default TasksSection;
