# 📱 Aplicativo de Faturamento Anual

Este é um aplicativo Android simples que permite ao usuário adicionar ou excluir valores de faturamento associados a um determinado ano. O saldo anual é exibido em tempo real com base nos dados armazenados localmente.

## 🔐 Persistência de Dados

Os dados de faturamento são **salvos de forma persistente utilizando `SharedPreferences`**, garantindo que as informações do usuário sejam mantidas mesmo após o fechamento do aplicativo.

## 🧰 Funcionalidades

- Seleção de ano via `NumberPicker` (de 2000 a 2025)
- Entrada de valor numérico para faturamento
- Opções para **adicionar** ou **excluir** valores do ano selecionado
- Exibição em tempo real do saldo total do ano
- Armazenamento local dos valores por ano usando `SharedPreferences`

## ▶️ Execução

1. Clone o repositório ou importe o projeto no Android Studio.
2. Conecte um dispositivo ou use um emulador.
3. Execute o app (`Run > Run 'app'`).
4. Use os campos para selecionar o ano, inserir o valor e definir a ação (adicionar ou excluir).
5. Clique em **Confirmar** e veja o saldo atualizado.

## 🧠 Observações Técnicas

- Os dados são salvos no arquivo `SharedPreferences` chamado `"MeusDados"`.
- Cada valor é vinculado ao ano selecionado como chave.
- O saldo não pode ser negativo; qualquer tentativa de exclusão que gere valor abaixo de zero resultará em saldo `R$ 0,00`.

---

Desenvolvido como exercício de prática Android com foco em manipulação de entrada de dados, `NumberPicker`, `RadioGroup` e persistência com `SharedPreferences`.
