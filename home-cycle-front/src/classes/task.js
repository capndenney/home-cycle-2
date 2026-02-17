export default class Task {
  constructor(id, title, description, dueDate, createdDate, completed = false) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.dueDate = dueDate;
    this.createdDate = createdDate;
    this.completed = completed;
  }
}
