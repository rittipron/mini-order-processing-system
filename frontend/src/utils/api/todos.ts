import { type Todo } from '@/interfaces/todo'

export async function fetchTodos(): Promise<Todo[]> {
  const res = await fetch('https://jsonplaceholder.typicode.com/todos?_limit=5')
  if (!res.ok) throw new Error('Failed to fetch todos')
  return res.json()
}