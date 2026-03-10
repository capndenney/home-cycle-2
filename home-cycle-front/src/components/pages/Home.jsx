import TasksSection from "../layout/TasksSection";
import { useState } from "react";
import { FilterPane } from "../layout/FilterPane";

const Home = ({ taskArray, saveTask, triggerRefresh }) => {
  const [filterStatus, setFilterStatus] = useState("All");
  const [dueWithinDays, setDueWithinDays] = useState("");
  const [onlyRecurring, setOnlyRecurring] = useState(false);

  const filteredTasks = taskArray.filter((task) => {
    const statusMatch =
      filterStatus === "All" ||
      (filterStatus === "Completed" && task.completed) ||
      (filterStatus === "Incomplete" && !task.completed);
    const recurMatch = !onlyRecurring || task.recurrence;

    let dateMatch = true;
    if (dueWithinDays) {
      const endDate = new Date();
      endDate.setDate(endDate.getDate() + parseInt(dueWithinDays));
      const dueDate = new Date(task.dueDate);
      dateMatch =
        dueDate <= endDate && dueDate >= new Date().setHours(0, 0, 0, 0);
    }
    return statusMatch && recurMatch && dateMatch;
  });

  return (
    <>
      <FilterPane
        filterStatus={filterStatus}
        setFilterStatus={setFilterStatus}
        dueWithinDays={dueWithinDays}
        setDueWithinDays={setDueWithinDays}
        onlyRecurring={onlyRecurring}
        setOnlyRecurring={setOnlyRecurring}
      />
      <TasksSection
        taskArray={filteredTasks}
        saveTask={saveTask}
        triggerRefresh={triggerRefresh}
      />
    </>
  );
};

export default Home;
