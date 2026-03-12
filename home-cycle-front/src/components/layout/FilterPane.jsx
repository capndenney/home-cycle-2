import Button from "../common/Button";

export const FilterPane = ({ filterStatus, setFilterStatus, dueWithinDays, setDueWithinDays, onlyRecurring, setOnlyRecurring }) => {
  return (
    <div className="filter-bar">
      <div className="filter-group">
        <label htmlFor="status-filter">Status: </label>
        <select 
          id="status-filter" 
          value={filterStatus} 
          onChange={(e) => setFilterStatus(e.target.value)}
        >
          <option value="All">All</option>
          <option value="Incomplete">Incomplete</option>
          <option value="Completed">Completed</option>
        </select>
      </div>
      <div className="filter-group">
        <label htmlFor="date-filter">Due within: </label>
        <select 
          id="date-filter" 
          value={dueWithinDays} 
          onChange={(e) => setDueWithinDays(e.target.value)}
        >
          <option value="">Anytime</option>
          <option value="3">3 Days</option>
          <option value="7">1 Week</option>
          <option value="30">1 Month</option>
        </select>
      </div>
      <div className="filter-group">
        <label>
          <input 
            type="checkbox" 
            checked={onlyRecurring} 
            onChange={(e) => setOnlyRecurring(e.target.checked)} 
          />
          Recurring Only
        </label>
      </div>
    </div>
  );
};