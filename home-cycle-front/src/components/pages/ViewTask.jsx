import TaskCard from "../common/TaskCard.JSX";
import { useParams } from "react-router";
import { useState, useEffect } from "react";

const ViewTask = ({ taskArray, saveTask }) => {
  const { id } = useParams();
  const localTaskId = Number(id);
  const [task, setTask] = useState(null);

  useEffect(() => {
    const foundTask = taskArray.find((t) => t.taskId === localTaskId);
    setTask(foundTask);
  }, [localTaskId, taskArray]);

  if (!task) {
    <div>Attempting to load your task</div>;
  } else {
    return (
      <TaskCard
        key={task.taskId}
        taskId={task.taskId}
        title={task.title}
        description={task.description}
        dueDate={task.dueDate}
        completed={task.completed}
        saveTask={saveTask}
      />
    );
  }
};

export default ViewTask;
