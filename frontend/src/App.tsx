import { useSelector, useDispatch } from 'react-redux'
import type { RootState, AppDispatch } from '@/store'
import { increment, decrement, incrementAsync } from '@/store/slices/counterSlice'
import { useQuery } from '@tanstack/react-query'
import { fetchTodos } from '@/utils/api/todos'
import Button from '@mui/material/Button'
import { type Todo } from '@/interfaces/todo'

function App() {
  const count = useSelector((state: RootState) => state.counter.value)
  const dispatch = useDispatch<AppDispatch>()

  const { data, isLoading, error } = useQuery<Todo[], Error>({
    queryKey: ['todos'],
    queryFn: fetchTodos,
  })

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100 p-4 text-center">
      <h1 className="text-3xl font-bold mb-6">Redux Saga + React Query + MUI + Tailwind</h1>

      <p className="text-2xl mb-4">Counter: {count}</p>
      <div className="space-x-2 mb-8">
        <Button variant="contained" onClick={() => dispatch(increment())}>+</Button>
        <Button variant="outlined" onClick={() => dispatch(decrement())}>-</Button>
        <Button variant="text" onClick={() => dispatch(incrementAsync(1))}>+ Async (Saga)</Button>
      </div>

      <h2 className="text-xl font-semibold mb-2">Todos (React Query)</h2>

      {isLoading && <p>Loading todos...</p>}
      {error && <p className="text-red-500">Error loading todos</p>}
      <ul className="list-disc list-inside max-w-md mx-auto">
        {data?.map(todo => (
          <li key={todo.id} className={todo.completed ? 'line-through' : ''}>
            {todo.title}
          </li>
        ))}
      </ul>
    </div>
  )
}

export default App
