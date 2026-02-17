import Card from "./Card";
import Input from "./forms/Input";
import Button from "./Button";
import { DayPicker } from "react-day-picker";
import { useState } from "react";
import { useParams, useNavigate } from "react-router";

const EditTask = ({ saveTask, tasks }) => {
  const { id } = useParams();

  const newTaskId =
    tasks.reduce((max, t) => {
      return t.taskId > max ? t.taskId : max;
    }, 0) + 1;

  const taskNum = id ? Number(id) : newTaskId;
  const loadTask = tasks.find((t) => t.taskId === taskNum);

  const getInitialTaskData = () => {
    if (loadTask) {
      const loadedDate = new Date(loadTask.dueDate);

      return {
        title: loadTask.title || "",
        taskId: loadTask.taskId || newTaskId,
        description: loadTask.description || "",
        completed: loadTask.completed || false,
        dueDate: loadedDate || null,
      };
    } else {
      return {
        title: "",
        taskId: newTaskId,
        description: "",
        completed: false,
        dueDate: null,
      };
    }
  };

  const initialTaskData = getInitialTaskData();

  const [loadedTaskData] = useState(initialTaskData);
  const [descData, setDescData] = useState(initialTaskData.description);
  const [compData, setCompData] = useState(initialTaskData.completed);
  const [titleData, setTitleData] = useState(initialTaskData.title);
  const [dueData, setDueData] = useState(initialTaskData.dueDate);

  const titleChange = (e) => {
    setTitleData(e.target.value);
  };

  const handleDescChange = (e) => {
    setDescData(e.target.value);
  };

  const handleCheck = (e) => {
    setCompData(e.target.checked);
  };

  const navigate = useNavigate();

  const handleSave = (e) => {
    e.preventDefault();
    const dateForSave = dueData ? dueData.toISOString() : null;

    const editedTask = {
      taskId: loadedTaskData.taskId,
      title: titleData,
      description: descData,
      dueDate: dateForSave,
      createdDate: new Date(),
      completed: compData,
    };

    saveTask(editedTask);
    setTimeout(() => {
      navigate(`/task/${loadedTaskData.taskId}`);
    });
  };

  const handleCancel = (e) => {
    e.preventDefault();
    setDescData(loadedTaskData.description);
    setCompData(loadedTaskData.completed);
    setTitleData(loadedTaskData.title);
    setDueData(loadedTaskData.dueDate);
  };

  return (
    <Card viewType="edit add-blur">
      <h3>{titleData}</h3>
      <p>Task ID: {loadedTaskData.taskId}</p>
      <Input
        label="Title"
        id={`input-title-${loadedTaskData.taskId}`}
        value={titleData}
        handleChange={titleChange}
        required={true}
      />
      <Input
        label="Description"
        value={descData}
        handleChange={handleDescChange}
      />
      <Input
        type="checkbox"
        checked={compData}
        label="Completed:"
        handleChange={handleCheck}
      />
      <h4>Due Date:</h4>
      <DayPicker
        mode="single"
        selected={dueData}
        onSelect={setDueData}
        footer={
          dueData
            ? `Due Date: ${dueData.toLocaleDateString()}`
            : `Please Select a Due Date`
        }
      />
      <Button label="Save" handleClick={handleSave}/>
      <Button label="Cancel" handleClick={handleCancel}/>
    </Card>
  );
};

export default EditTask;
