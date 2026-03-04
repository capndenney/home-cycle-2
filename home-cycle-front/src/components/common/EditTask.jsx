import Card from "./Card";
import Input from "./forms/Input";
import Button from "./Button";
import { DayPicker } from "react-day-picker";
import { useState } from "react";
import { useParams, useNavigate } from "react-router";
import { taskService } from "../services/taskService";

// TODO: Add field for recurrence tracking

const EditTask = ({ saveTask, tasks }) => {
  const { id } = useParams();

  // TODO: Delete when tied in to back end. 
  // const newTaskId =
  //   tasks.reduce((max, t) => {
  //     return t.taskId > max ? t.taskId : max;
  //   }, 0) + 1;

  const loadTask = id ? tasks.find((t) => t.id === Number(id)) : null;

  const getInitialTaskData = () => {
    if (loadTask) {
      return {
        title: loadTask.title || "",
        id: loadTask.id,
        description: loadTask.description || "",
        completed: loadTask.completed || false,
        dueDate: loadTask.dueDate ? new Date(loadTask.dueDate) : null
      };
    }
      return {
        title: "",
        id: null,
        description: "",
        completed: false,
        dueDate: null,
      };
  
  };

  // const initialTaskData = getInitialTaskData(); // TODO: Delete where Sample data is being pulled.

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

  const handleSave = async (e) => {
    e.preventDefault();
    const dateForSave = dueData ? dueData.toISOString().split('T')[0] : null;

    const taskDto = {
      // taskId: loadedTaskData.taskId, TODO: Remove, this is not in the DTO.
      title: titleData,
      description: descData,
      dueDate: dateForSave,
      // createdDate: new Date(), TODO: Remove, this is being done on the back end.
      completed: compData,
      householdId: 1, // TODO: Replace with automated data later
      createdBy: 2, // TODO: Replace with automated data later
      recurrence: 0 // TODO: Tie in to recurrence field when added
    };

    try {
      let savedTask;
      if (id) {
        // Update when id exists
        const response = await taskService.updateTask(id, taskDto);
        savedTask = response.data;
      } else {
        // Create when no id exists
        const response = await taskService.create(taskDto);
        savedTask = response.data;
      }
      navigate(`/task/${savedTask.id}`);
    } catch (er) {
      console.error("Error saving task:", er);
      // TODO: If time, add in error handling to display message to user on failure.
    }

    saveTask(taskDto);
    // setTimeout(() => {
    //   navigate(`/task/${loadedTaskData.taskId}`);
    // });
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
