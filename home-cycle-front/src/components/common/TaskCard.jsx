import Card from "./Card";
import Button from "./Button";
import { useNavigate } from "react-router";

const TaskCard = ({
  saveTask,
  taskId,
  title,
  description,
  dueDate,
  completed,
}) => {
  const loadedId = taskId;
  const loadedDate = new Date(dueDate);
  const formattedDate = loadedDate.toLocaleDateString();
  const navigate = useNavigate();
  const handleEditButton = () => navigate(`/task/${loadedId}/edit`);
  const handleComplete = () => {
    const updatedTask = {
      taskId: loadedId,
      title: title ,
      description: description,
      dueDate: dueDate,
      completed: true,
    };
    saveTask(updatedTask);
  };

  return (
    <Card viewType="view" id={loadedId} taskId={loadedId}>
      <h3>{title}</h3>
      <p>Task ID: {loadedId}</p>
      <p>{description}</p>
      <p>Completed:({completed ? `\u2705` : `\u274C`})</p>
      <p>Due Date: {formattedDate}</p>
      <Button
        id={`edit-task-${loadedId}`}
        label="Edit"
        handleClick={handleEditButton}
      />
      {!completed && (
        <Button
          id={`complete-task-${loadedId}`}
          label="Complete"
          handleClick={handleComplete}
        />
      )}
    </Card>
  );
};

export default TaskCard;
