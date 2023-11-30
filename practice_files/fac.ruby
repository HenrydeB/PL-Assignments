def prompt(message)
    puts "=> #{message}"
end

def factorial(num)
    if num == 0
        1
    else 
        num * factorial(num -1)
    end
end

loop do
    user_input = gets.chomp.to_i
end