class TodoItemService {
  constructor(todoItemRepository) {
    this.todoItemRepository = todoItemRepository;
  }

  async getAll() {
    return await this.todoItemRepository.findAll();
  }

  async getById(id) {
    const item = await this.todoItemRepository.findById(id);
    if (!item) {
      const error = new Error(`Todo item with id ${id} not found`);
      error.status = 404;
      throw error;
    }
    return item;
  }

  async create(data) {
    // Basic validation
    if (!data.title) {
      const error = new Error('Title is required');
      error.status = 400;
      throw error;
    }
    const newItem = {
      title: data.title,
      description: data.description || '',
      completed: false,
      createdAt: new Date(),
      updatedAt: new Date()
    };
    return await this.todoItemRepository.save(newItem);
  }

  async update(id, data) {
    const existing = await this.getById(id);
    const updated = {
      ...existing,
      title: data.title !== undefined ? data.title : existing.title,
      description: data.description !== undefined ? data.description : existing.description,
      completed: data.completed !== undefined ? data.completed : existing.completed,
      updatedAt: new Date()
    };
    return await this.todoItemRepository.update(id, updated);
  }

  async delete(id) {
    const existing = await this.getById(id);
    await this.todoItemRepository.delete(id);
    return existing;
  }
}

module.exports = TodoItemService;
