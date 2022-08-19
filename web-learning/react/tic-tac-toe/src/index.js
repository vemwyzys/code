import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';

class Square extends React.Component {
  render() {
    return (
      <button
        className="square"
        onClick={() => this.props.onClick()}
      >
        {this.props.value}
      </button>
    );
  }
}

class Board extends React.Component {
  renderSquare(i) {
    return (
      <Square value={this.props.squares[i]}
        onClick={() => this.props.onClick(i)}
      />
    );
  }

  render() {
    return (
      <div>
        <div className="board-row">
          {this.renderSquare(0)}
          {this.renderSquare(1)}
          {this.renderSquare(2)}
        </div>
        <div className="board-row">
          {this.renderSquare(3)}
          {this.renderSquare(4)}
          {this.renderSquare(5)}
        </div>
        <div className="board-row">
          {this.renderSquare(6)}
          {this.renderSquare(7)}
          {this.renderSquare(8)}
        </div>
      </div>
    );
  }
}

class Game extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      history: [{
        squares: Array(9).fill(null),
      }],
      stepNumber: 0,
      xIsNext: true,
    }
  }

  jumpTo(step) {
    this.setState({
      // 这是因为 state 更新被合并了，或者用更简单的话说，
      // React 不会更新 setState 方法中未提到的属性
      stepNumber: step,
      xIsNext: (step % 2) === 0,
    })
  }

  handleClick(i) {
    const history = this.state.history.slice(0, this.state.stepNumber + 1);
    const current = history[history.length - 1];
    const squares = current.squares.slice();
    if (calculateWinner(squares) || squares[i]) {
      return;
    }
    squares[i] = this.state.xIsNext ? 'X' : 'O';
    this.setState({
      history: history.concat([{ squares: squares }]),
      xIsNext: !this.state.xIsNext
    })
  }

  render() {
    const history = this.state.history;
    const current = history[this.state.stepNumber];
    const winner = calculateWinner(current.squares)

    const moves = history.map((step, move) => {
      const desc = move ?
        'Go to move #' + move :
        'Go to game start';
      return (
        <li key={move}>
          <button onClick={() => this.jumpTo(move)}>{desc}</button>
        </li>
      )
    })

    let status;
    if (winner) {
      status = 'Winner: ' + winner;
    } else {
      status = 'Next player: ' + (this.state.xIsNext ? 'X' : 'O');
    }
    return (
      <div>
        <div className="game">
          <div className="game-board">
            <h1>*tic tac toe*</h1>
            <Board
              squares={current.squares}
              onClick={(i) => this.handleClick(i)}
            />
          </div>
          <div className="game-info">
            <div>{status}</div>
            <ol>{moves}</ol>
          </div>
        </div>
        <Clock />
        <Toggle />
        <SameIf />
        <SameList />
        <NameForm />
        <Calculator />
      </div>
    );
  }
}

// ========================================

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(<Game />);

function calculateWinner(squares) {
  const lines = [
    [0, 1, 2],
    [3, 4, 5],
    [6, 7, 8],
    [0, 3, 6],
    [1, 4, 7],
    [2, 5, 8],
    [0, 4, 8],
    [2, 4, 6],
  ];
  for (let i = 0; i < lines.length; i++) {
    const [a, b, c] = lines[i];
    if (squares[a]
      && squares[a] === squares[b]
      && squares[a] === squares[c]) {
      return squares[a];
    }
  }
  return null;
}

// ===============================================
// state & 生命周期
class Clock extends React.Component {
  constructor(props) {
    super(props);
    this.state = { date: new Date() }
  }

  componentDidMount() {
    this.timeID = setInterval(
      () => this.tick(),
      1000
    )
  }

  tick() {
    this.setState({
      date: new Date()
    })
  }

  componentWillUnmount() {
    clearInterval(this.timeID)
  }

  render() {
    return (
      <div>
        <h1>*生命周期*</h1>
        <h2>It is {this.state.date.toLocaleTimeString()}</h2>
      </div>
    )
  }
}

// ===============================================
// 事件处理
class Toggle extends React.Component {
  constructor(props) {
    super(props);
    this.state = { isToggleOn: true };

    // 你必须谨慎对待 JSX 回调函数中的 this，
    // 在 JavaScript 中，class 的方法默认不会绑定 this。
    // 如果你忘记绑定 this.handleClick 并把它传入了 onClick，
    // 当你调用这个函数的时候 this 的值为 undefined。

    // bind只有一个函数，且不会立刻执行，
    // 只是将一个值绑定到函数的this上,并将绑定好的函数返回
    this.handleClick = this.handleClick.bind(this);
  }

  handleClick() {
    this.setState(prevState => ({
      isToggleOn: !prevState.isToggleOn
    }));
  }

  // 使用public class fields语法解决this绑定问题
  handleClinkInBetterWay = () => {
    console.log('public class fields way')
    this.setState(prevState => ({
      isToggleOn: !prevState.isToggleOn
    }));
  }

  render() {
    return (
      <div>
        <h1>*事件处理*</h1>
        <button onClick={this.handleClick}>
          {this.state.isToggleOn ? 'ON' : 'OFF'}
        </button>
      </div>
    )
  }


}

// 条件渲染
class SameIf extends React.Component {
  render() {
    const flag = false;
    return (
      <div>
        <h1>*条件渲染*</h1>
        {flag &&
          <h2>
            someIf is true
          </h2>
        }
        {!flag &&
          <h2>
            someIf is true
          </h2>
        }
        {!flag
          ? <h2> 三目</h2>
          : <h2> 三目</h2>
        }
      </div>
    )
  }
}

// =============================================
class SameList extends React.Component {
  render() {
    const numbers = [1, 2, 3];
    const listItems = numbers.map((number) =>
      <li key={number.toString()}>{number}</li>
    )
    return (
      <div>
        <h1>*列表组件*</h1>
        <ul>{listItems}</ul>
      </div>
    )
  }
}

// =================================================
class NameForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = { value: '' };
  }

  handleChange = (e) => {
    this.setState({ value: e.target.value })
  }

  handleSubmit = (e) => {
    console.log('submit name: ' + this.state.value);
    e.preventDefault();
  }

  render() {
    return (
      <form onSubmit={this.handleSubmit}>
        <label>
          name:
          <textarea value={this.state.value} onChange={this.handleChange} />
        </label>
        <input
          type='submit'
          value='submit'
        />
      </form>
    )
  }
}

// =================================================
function BoilingVerdict(props) {
  if (props.celsius >= 100) {
    return <p>the water would boil.</p>
  }
  return <p>the water would not boil</p>
}

const scaleNames = {
  c: 'Celsius',
  f: 'Fahrenheit'
}

class TemperatureInput extends React.Component {
  handleChange = (e) => {
    this.props.onTemperatureChange(e.target.value)
  }

  render() {
    const temperature = this.props.temperature
    const scale = this.props.scale
    return (
      <fieldset>
        <legend>Enter temperature in {scaleNames[scale]}:</legend>
        <input value={temperature}
          onChange={this.handleChange} />
        <BoilingVerdict celsius={parseFloat(temperature)} />
      </fieldset>
    )
  }
}

class Calculator extends React.Component {
  constructor(props) {
    super(props)
    this.state = { temperature: '', scale: 'c' }
  }

  handleCelsiusChange = (temperature) => {
    this.setState({ scale: 'c', temperature })
  }
  handleFahrenheitChange = (temperature) => {
    this.setState({ scale: 'f', temperature })
  }

  render() {
    const scale = this.state.scale
    const temperature = this.state.temperature
    const celsius = scale === 'f'
      ? tryConvert(temperature, toCelsius)
      : temperature
    const fahrenheit = scale === 'c'
      ? tryConvert(temperature, toFahrenheit)
      : temperature
    return (
      <div>
        <TemperatureInput
          scale='c'
          temperature={celsius}
          onTemperatureChange={this.handleCelsiusChange} />
        <TemperatureInput
          scale='f'
          temperature={fahrenheit}
          onTemperatureChange={this.handleFahrenheitChange} />
        <BoilingVerdict
          celsius={parseFloat(celsius)}/>
      </div>
    )
  }
}
function toCelsius(fahrenheit) {
  return (fahrenheit - 32) * 5 / 9
}
function toFahrenheit(celsiue) {
  return (celsiue * 9 / 5) + 32
}
function tryConvert(temperature, convert) {
  const input = parseFloat(temperature)
  if (Number.isNaN(input)) {
    return '';
  }
  const output = convert(input)
  const rounded = Math.round(output * 1000) / 1000
  return rounded.toString()
}