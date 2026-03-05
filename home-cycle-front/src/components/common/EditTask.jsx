import Card from "./Card";
import Input from "./forms/Input";
import Button from "./Button";
import { DayPicker } from "react-day-picker";
import { useState } from "react";
import { useParams, useNavigate } from "react-router";
import { taskService } from "../services/taskService";

const EditTask = ({ saveTask, tasks, removeTaskFromState, triggerRefresh }) => {
  const { id } = useParams();

  const loadTask = id ? tasks.find((t) => t.id === Number(id)) : null;

  const getInitialTaskData = () => {

    let initialRecur = 0;
    let initialUnit = 1;

    if (loadTask && loadTask.recurrence > 0) {
      const total = loadTask.recurrence;
      if (total % 30 === 0) {
        initialRecur = total / 30;
        initialUnit = 30;
      } else if (total % 7 === 0) {
        initialRecur = total / 7;
        initialUnit = 7;
      } else {
        initialRecur = total;
        initialUnit = 1;
      }
    }

   if (loadTask) {
      return {
        title: loadTask.title || "",
        id: loadTask.id,
        description: loadTask.description || "",
        completed: loadTask.completed || false,
        dueDate: loadTask.dueDate ? new Date(loadTask.dueDate) : null,
        recurrence: initialRecur,
        unit: initialUnit,
      };
    }
    return {
      title: "",
      id: null,
      description: "",
      completed: false,
      dueDate: null,
      recurrence: 0,
      unit: 1,
    };
  };

  const initialTaskData = getInitialTaskData();

  const [loadedTaskData] = useState(initialTaskData);
  const [descData, setDescData] = useState(initialTaskData.description);
  const [compData, setCompData] = useState(initialTaskData.completed);
  const [titleData, setTitleData] = useState(initialTaskData.title);
  const [dueData, setDueData] = useState(initialTaskData.dueDate);
  const [recurData, setRecurData] = useState(initialTaskData.recurrence);
  const [unitData, setUnitData] = useState(initialTaskData.unit);

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
      title: titleData,
      description: descData,
      dueDate: dateForSave,
      completed: compData,
      householdId: localStorage.getItem("householdId") ? Number(localStorage.getItem("householdId")) : null,
      createdBy: Number(localStorage.getItem("userId")),
      recurrence: Number(recurData) * Number(unitData)
    };

    try {
      let savedTask;
      if (id) {
        // Update when id exists
        const response = await taskService.updateTask(id, taskDto);
        // check: if task service updates in future, may not need response.data, just response.
        savedTask = response.data;
        saveTask(savedTask);
      } else {
        // Create when no id exists
        const response = await taskService.create(taskDto);
        //
        savedTask = response.data;
        saveTask(savedTask);
      }
      triggerRefresh();
      navigate(`/`);
    } catch (er) {
      console.error("Error saving task:", er);
      triggerRefresh();
      // TODO: If time, add in error handling to display message to user on failure.
    }
  };

  const handleCancel = (e) => {
    e.preventDefault();
    setDescData(loadedTaskData.description);
    setCompData(loadedTaskData.completed);
    setTitleData(loadedTaskData.title);
    setDueData(loadedTaskData.dueDate);
  };

  const handleDelete = async (e) => {
    e.preventDefault();
    if (id && window.confirm("Are you sure you want to delete this task?")) {
      try {
        await taskService.deleteTask(id);
        removeTaskFromState(Number(id));
        navigate("/");
      } catch (er) {
        console.error("Error deleting task:", er);
      }
    }
  };

  return (
    <Card viewType="edit add-blur">
      <h3>{titleData}</h3>
      <p>Task ID: {loadedTaskData.id}</p>
      <Input
        label="Title"
        id={`input-title-${loadedTaskData.id}`}
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
      <Input
        type="number"
        value={recurData}
        label="Recurrence:"
        handleChange={(e) => setRecurData(e.target.value)}
      />
      <select
        value={unitData}
        onChange={(e) => setUnitData(e.target.value)}
      >
        <option value={1}>Days</option>
        <option value={7}>Weeks</option>
        <option value={30}>Months</option>
      </select>
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
      <Button label="Delete" handleClick={handleDelete} classes="btn-danger"/>
    </Card>
  );
};

export default EditTask;
